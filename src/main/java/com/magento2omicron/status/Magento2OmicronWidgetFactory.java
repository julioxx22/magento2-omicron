// Copyright 2000-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.magento2omicron.status;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.wm.StatusBarWidget;
import com.intellij.openapi.wm.impl.status.widget.StatusBarEditorBasedWidgetFactory;
import com.magento2omicron.ui.UIBundle;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

public class Magento2OmicronWidgetFactory extends StatusBarEditorBasedWidgetFactory {
  public static final String MAGENTO_2_OMICRON_STATUS = "Magento2OmicronStatus";
  @Override
  public @NotNull String getId() {
    return MAGENTO_2_OMICRON_STATUS;
  }

  @Override
  public @Nls @NotNull String getDisplayName() {
    return UIBundle.message("status.bar.magento2.omicron.widget.name");
  }

  @Override
  public @NotNull StatusBarWidget createWidget(@NotNull Project project) {
    return new Magento2OmicronStatusWidget(project);
  }

  @Override
  public void disposeWidget(@NotNull StatusBarWidget widget) {
    Disposer.dispose(widget);
  }
}
