/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.hp.mvp_sample;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;


import java.util.Objects;
import java.util.UUID;

/**
 * Immutable model class for a Task.
 */
public final class Task {
    private String id;
    private String title;
    private String content;
    private boolean complete;


    public Task() {

    }

    public Task(Task tsk) {
        this.id = tsk.id;
        this.title = tsk.title;
        this.content = tsk.content;
        this.complete = tsk.complete;
    }

    public Task(String title, String content) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.content = content;
        this.complete = false;
    }

    public Task(String title, String content, boolean complete) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.content = content;
        this.complete = complete;
    }

    public boolean getComplete(){
        return  complete;
    }

    public void changeComplete(){
       this.complete = !this.complete;
    }

    public String getId() {
        return  id;
    }

    public String getContent() {
        return  content;
    }

    public String getTitle() {
        return title;
    }
}
