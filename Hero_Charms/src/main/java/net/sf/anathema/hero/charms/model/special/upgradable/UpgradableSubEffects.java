package net.sf.anathema.hero.charms.model.special.upgradable;

import net.sf.anathema.hero.charms.model.special.subeffects.SubEffect;

public class UpgradableSubEffects extends CollectionSubEffects {
  public int getUpgradeBPCost() {
    int total = 0;
    for (SubEffect subeffect : this) {
      Upgrade upgrade = (Upgrade) subeffect;
      total += subeffect.isCreationLearned() ? upgrade.getBPCost() : 0;
    }
    return total;
  }

  public int getUpgradeXPCost() {
    int total = 0;
    for (SubEffect subeffect : this) {
      Upgrade upgrade = (Upgrade) subeffect;
      total += subeffect.isLearned() && !subeffect.isCreationLearned() ? upgrade.getXPCost() : 0;
    }
    return total;
  }
}