package lk.slt.marketplacer.service;

import java.net.URL;

public interface FileService {
    /**
     * Create a presigned URL for uploading with a PUT request.
     *
     * @param fileName - The name of the object.
     * @return - The presigned URL for an HTTP PUT.
     */
    public URL createUploadUrl(String fileName);

    /**
     * Create a presigned URL for downloading with a GET request.
     *
     * @param fileName - The name of the object.
     * @return - The presigned URL for an HTTP GET.
     */
    public URL createDownloadUrl(String fileName);
}
