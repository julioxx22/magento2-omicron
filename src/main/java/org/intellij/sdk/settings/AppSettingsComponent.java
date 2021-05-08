// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.sdk.settings;

import com.intellij.ui.components.JBCheckBox;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBTextField;
import com.intellij.util.ui.FormBuilder;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * Supports creating and managing a {@link JPanel} for the Settings Dialog.
 */
public class AppSettingsComponent {

  private final JPanel myMainPanel;
  private final JBTextField myMagentoCommand = new JBTextField();
  private final JBCheckBox myIdeaUserStatus = new JBCheckBox("Do you use IntelliJ IDEA? ");

  public AppSettingsComponent() {
    myMainPanel = FormBuilder.createFormBuilder()
            .addLabeledComponent(new JBLabel("Magento Executable Command: "), myMagentoCommand, 1, false)
            .addComponent(myIdeaUserStatus, 1)
            .addComponentFillVertically(new JPanel(), 0)
            .getPanel();
  }

  public JPanel getPanel() {
    return myMainPanel;
  }

  public JComponent getPreferredFocusedComponent() {
    return myMagentoCommand;
  }

  @NotNull
  public String getMagentoCommand() {
    return myMagentoCommand.getText();
  }

  public void setMagentoCommand(@NotNull String newText) {
    myMagentoCommand.setText(newText);
  }

  public boolean getIdeaUserStatus() {
    return myIdeaUserStatus.isSelected();
  }

  public void setIdeaUserStatus(boolean newStatus) {
    myIdeaUserStatus.setSelected(newStatus);
  }

}
