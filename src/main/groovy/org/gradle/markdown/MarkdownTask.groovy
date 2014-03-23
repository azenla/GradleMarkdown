package org.gradle.markdown

import groovy.io.FileType
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import org.pegdown.PegDownProcessor

class MarkdownTask extends DefaultTask {

    @InputDirectory
    File srcDir = new File("${project.rootDir}/src/markdown/")
    @OutputDirectory
    File outputDir = new File("${project.buildDir}/markdown/")

    @TaskAction
    void generate() {
        def processor = new PegDownProcessor()
        srcDir.eachFileRecurse(FileType.FILES) { file ->
            if (file.name.endsWith(".md") || file.name.endsWith(".markdown")) {
                def out = new File(outputDir, file.absolutePath.replace(srcDir.absolutePath, "").replace("\\.md", ".html").replace("\\.markdown", ".html"))
                out.parentFile.mkdirs()
                def content = processor.markdownToHtml(file.text)
                out.write(content)
            }
        }
    }
}
