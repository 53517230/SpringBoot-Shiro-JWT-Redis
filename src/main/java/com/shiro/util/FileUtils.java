package com.shiro.util;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author 大静是我女儿
 * @version 1.0
 * @date 2021年04月28日 16:33
 */
@Component
public class FileUtils {
  /**
   * 文件上传
   *
   * @param file
   * @return
   * @throws IOException
   */
  public static File uploadFile(MultipartFile file) throws IOException {
    String originalName = file.getOriginalFilename();
    String suffix = originalName.substring(originalName.lastIndexOf(".") + 1);
    if ("jpg".equalsIgnoreCase(suffix)
        || "jpeg".equalsIgnoreCase(suffix)
        || "png".equalsIgnoreCase(suffix)) {
      String projectPath = System.getProperty("user.dir");
      String directoryName = "uploadFile";
      File files = new File(projectPath, directoryName);
      if (!files.exists()) {
        files.mkdirs();
      }
      String targetFile = files.getAbsolutePath();
      String newFileName = UUIDUtils.getUUID() + "." + suffix;
      File newFile = new File(targetFile, newFileName);
      file.transferTo(newFile);
      return newFile;
    } else {
      throw new IllegalArgumentException("文件格式后缀错误");
    }
  }

  /**
   * 删除文件
   *
   * @param filePath
   * @return
   */
  public static boolean deleteFile(String filePath) {
    boolean flag = false;
    File file = new File(filePath);
    if (file.exists() && file.isFile()) {
      file.delete();
      flag = true;
    }
    return flag;
  }
}
