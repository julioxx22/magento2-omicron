// Copyright 2000-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.magento2omicron.status;

import com.intellij.openapi.util.SystemInfo;
import com.intellij.openapi.wm.impl.status.TextPanel;
import com.intellij.util.ui.JBFont;
import com.intellij.util.ui.JBUI;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class Magento2OmicronStatusBarPanel extends JPanel {
  private final TextPanel myLabel;
  private final JLabel myIconLabel;

  public Magento2OmicronStatusBarPanel() {
    super();
    setOpaque(false);
    setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
    setAlignmentY(Component.CENTER_ALIGNMENT);
    myIconLabel = new JLabel("");
    myIconLabel.setBorder(JBUI.Borders.empty(2,2,2,0));
    add(myIconLabel);
    myLabel = new TextPanel() {};
    myLabel.setFont(SystemInfo.isMac ? JBUI.Fonts.label(11) : JBFont.label());
    add(myLabel);
    setBorder(JBUI.Borders.empty(0));
  }

  public void setText(@NotNull @Nls String text) {
    myLabel.setText(text);
  }

  @Nullable
  public String getText() {
    return myLabel.getText();
  }

  public void setIcon(@Nullable Icon icon) {
    myIconLabel.setIcon(icon);
    myIconLabel.setVisible(icon != null);
  }
}
