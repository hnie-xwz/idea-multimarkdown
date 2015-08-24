/*
 * Copyright (c) 2015-2015 Vladimir Schneider <vladimir.schneider@gmail.com>
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 * This file is based on the IntelliJ SimplePlugin tutorial
 *
 */
package com.vladsch.idea.multimarkdown;

import com.intellij.openapi.fileTypes.LanguageFileType;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class MarkdownFileType extends LanguageFileType {
    public static final MarkdownFileType INSTANCE = new MarkdownFileType();

    private MarkdownFileType() {
        super(MarkdownLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return MarkdownBundle.message("multimarkdown.filetype.name");
    }

    @NotNull
    @Override
    public String getDescription() {
        return MarkdownBundle.message("multimarkdown.filetype.description");
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return MarkdownFileTypeFactory.DEFAULT_EXTENSION;
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return MarkdownIcons.FILE;
    }
}
