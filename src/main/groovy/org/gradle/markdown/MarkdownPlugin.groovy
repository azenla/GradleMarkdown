package org.gradle.markdown

import org.gradle.api.Plugin
import org.gradle.api.Project

class MarkdownPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        def markdown = project.tasks.create("markdown", MarkdownTask)
        project.extensions.add("markdown", markdown)
    }
}
