// Copyright 2000-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.magento2omicron.status;

import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.ui.popup.ListPopup;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.StatusBarWidget;
import com.intellij.openapi.wm.impl.status.EditorBasedStatusBarPopup;
import com.magento2omicron.ui.UIBundle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class Magento2OmicronStatusWidget extends EditorBasedStatusBarPopup {

  /**
   * Icon
   */
  Icon icon = IconLoader.getIcon("/icons/magento2.svg", Magento2OmicronStatusWidget.class);
  private Magento2OmicronStatusBarPanel myPanel;

  protected Magento2OmicronStatusWidget(@NotNull Project project) {
    super(project, true);

  }

  @NotNull
  @Override
  protected WidgetState getWidgetState(@Nullable VirtualFile file) {
    if (file == null) {
      return WidgetState.HIDDEN;
    }
    String toolTipText = UIBundle.message("tooltip.magento2.omicron");
    String panelText = " " + UIBundle.message("status.bar.magento2.omicron.widget.panel.text");

    return new WidgetState(toolTipText, panelText, true);
  }

  @Nullable
  public Icon getIcon() {
    return IconLoader.getIcon("/icons/magento2.svg", Magento2OmicronStatusWidget.class);
  }

  @Nullable
  @Override
  protected ListPopup createPopup(DataContext context) {
    AnAction group = ActionManager.getInstance().getAction("ChangeLineSeparators");
    if (!(group instanceof ActionGroup)) {
      return null;
    }

    return JBPopupFactory.getInstance().createActionGroupPopup(
      UIBundle.message("status.bar.magento2.omicron.widget.name"),
      (ActionGroup)group,
      context,
      JBPopupFactory.ActionSelectionAid.SPEEDSEARCH,
      false
    );
  }

  @NotNull
  @Override
  protected StatusBarWidget createInstance(@NotNull Project project) {
    return new Magento2OmicronStatusWidget(project);
  }

  @NotNull
  @Override
  public String ID() {
    return Magento2OmicronWidgetFactory.MAGENTO_2_OMICRON_STATUS;
  }

  @Override
  protected JPanel createComponent() {
    myPanel = new Magento2OmicronStatusBarPanel();
    return myPanel;
  }

  @Override
  protected void updateComponent(@NotNull WidgetState state) {
    myPanel.setIcon(this.icon);
    myPanel.setText(state.getText());
    myPanel.setToolTipText(state.getToolTip());
  }
}
