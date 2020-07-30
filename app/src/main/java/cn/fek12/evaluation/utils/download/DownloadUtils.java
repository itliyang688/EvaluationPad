package cn.fek12.evaluation.utils.download;

import android.util.Log;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadSampleListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.liulishuo.filedownloader.util.FileDownloadUtils;

import java.io.File;

public class DownloadUtils {

    private BaseDownloadTask singleTask ;
    private FileDownLoaderCallBack mCallBack;
    public int singleTaskId = 0;
    public String mSinglePath;
    public String mSaveFolder;

    /**
     * 单任务下载
     *
     * @param downLoadUri    文件下载网络地址
     * @param destinationUri 下载文件的存储绝对路径
     */
    public void startDownLoadFileSingle(String downLoadUri, String destinationUri,String SaveFolder, FileDownLoaderCallBack callBack) {
        this.mSinglePath = destinationUri;
        String url = downLoadUri;
        this.mCallBack = callBack;
        this.mSaveFolder = SaveFolder;
        delete_single();
        Log.d("feifei",destinationUri);
        singleTask = FileDownloader.getImpl().create(url)
                .setPath(destinationUri)
                .setCallbackProgressTimes(300)
                .setMinIntervalUpdateSpeed(400)
                .setListener(fileDownloadSampleListener);

        singleTaskId =  singleTask.start();
    }


    public interface FileDownLoaderCallBack {
        //文件是否下载完成
        void downLoadCompleted(BaseDownloadTask task);

        //文件是否下载失败
        void downLoadError(BaseDownloadTask task, Throwable e);

        //文件下载进度
        void downLoadProgress(String progress);
    }


    private FileDownloadSampleListener fileDownloadSampleListener = new FileDownloadSampleListener(){
        @Override
        protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
            Log.d("DownloadTask","pending taskId:"+task.getId()+",soFarBytes:"+soFarBytes+",totalBytes:"+totalBytes+",percent:"+soFarBytes*1.0/totalBytes);
        }

        @Override
        protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
           if(mCallBack != null){
                int percent=(int) ((double) soFarBytes / (double) totalBytes * 100);
                mCallBack.downLoadProgress(String.valueOf(percent));
            }
        }

        @Override
        protected void blockComplete(BaseDownloadTask task) {
            if(mCallBack != null){
                mCallBack.downLoadCompleted(task);
            }
        }

        @Override
        protected void completed(BaseDownloadTask task) {
            Log.d("DownloadTask","completed taskId:"+task.getId()+",isReuse:"+task.reuse());
        }

        @Override
        protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {
            Log.d("DownloadTask","paused taskId:"+task.getId()+",soFarBytes:"+soFarBytes+",totalBytes:"+totalBytes+",percent:"+soFarBytes*1.0/totalBytes);
        }

        @Override
        protected void error(BaseDownloadTask task, Throwable e) {
            if(mCallBack != null){
                mCallBack.downLoadError(task,e);
            }
        }

        @Override
        protected void warn(BaseDownloadTask task) {

        }
    };


    public void pause_single(){
        Log.d("DownloadTask","pause_single task:"+singleTaskId);
        FileDownloader.getImpl().pause(singleTaskId);
    }

    public void delete_single(){

        //删除单个任务的database记录
        boolean deleteData =  FileDownloader.getImpl().clear(singleTaskId,mSaveFolder);
        //将下载的文件和临时文件删除
        File targetFile = new File(mSinglePath);
        boolean delate = false;
        if(targetFile.exists()){
            delate = targetFile.delete();
        }

        Log.d("DownloadTask","delete_single file,deleteDataBase:"+deleteData+",mSinglePath:"+mSinglePath+",delate:"+delate);

        new File(FileDownloadUtils.getTempPath(mSinglePath)).delete();
    }
}
