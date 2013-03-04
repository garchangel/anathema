package net.sf.anathema.scribe.perspective.model;

import net.sf.anathema.campaign.module.NoteTypeConfiguration;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.repository.IRepository;
import net.sf.anathema.framework.repository.access.printname.IPrintNameFileAccess;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.scribe.editor.model.ScrollModel;

import java.util.Collection;

public class ScribeModel {
  public final ScrollModel scrollModel = new ScrollModel();
  private IApplicationModel applicationModel;

  public ScribeModel(IApplicationModel applicationModel) {
    this.applicationModel = applicationModel;
  }

  public Collection<PrintNameFile> collectAllScrolls() {
    IItemType itemType = NoteTypeConfiguration.ITEM_TYPE;
    IRepository repository = applicationModel.getRepository();
    IPrintNameFileAccess access = repository.getPrintNameFileAccess();
    return access.collectAllPrintNameFiles(itemType);
  }
}