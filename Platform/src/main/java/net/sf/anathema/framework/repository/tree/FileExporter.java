package net.sf.anathema.framework.repository.tree;

import net.sf.anathema.framework.repository.access.IRepositoryFileAccess;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.framework.environment.Resources;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileExporter {
  private RepositoryZipPathCreator creator;
  private ExportModel model;
  private Resources resources;

  public FileExporter(RepositoryZipPathCreator repositoryZipPathCreator, ExportModel model, Resources resources) {
    this.creator = repositoryZipPathCreator;
    this.model = model;
    this.resources = resources;
  }

  public PrintNameFile[] exportToZip(Path saveFile) throws IOException {
    ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(saveFile));
    zipOutputStream.setComment(resources.getString("Anathema.Version.Numeric"));
    PrintNameFile[] printNameFiles = model.getPrintNameFilesInSelection();
    for (PrintNameFile printNameFile : printNameFiles) {
      IRepositoryFileAccess access = model.getFileAccess(printNameFile);
      for (File file : access.getFiles()) {
        ZipEntry entry = createZipEntry(file, printNameFile);
        InputStream input = access.openInputStream(file);
        zipOutputStream.putNextEntry(entry);
        IOUtils.copy(input, zipOutputStream);
        zipOutputStream.closeEntry();
        input.close();
      }
    }
    zipOutputStream.close();
    return printNameFiles;
  }

  ZipEntry createZipEntry(File file, PrintNameFile printNameFile) {
    ZipEntry entry = new ZipEntry(creator.createZipPath(file));
    entry.setComment(resources.getString(
            "Anathema.Version.Numeric") + "#" + printNameFile.getItemType() + "#" + printNameFile.getRepositoryId());
    return entry;
  }
}