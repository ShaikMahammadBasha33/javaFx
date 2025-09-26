package com.epam.library;

import javafx.application.Platform;

public abstract class JavaFXTestBase {
    static {
        try {
            Platform.startup(() -> {});  // called only once
        } catch (IllegalStateException e) {
            // already initialized, ignore
        }
    }
}
