package li.raphael.kotlinwebcomponents

import kotlinx.html.html
import kotlinx.html.stream.appendHTML
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ComponentTest {

    private val document = Document(Context("X")).apply {
        navigation {
            item("Link A", "/a")
            item("B", "/b")
            item(context.linkName, "/x")
        }
    }

    @Test
    fun checkTxtSnapshot() {
        assertThat(document.toString()).isEqualTo(
            """
            <Document>
              <Navigation>
                <NavigationItem name="Link A" url="/a" />
                <NavigationItem name="B" url="/b" />
                <NavigationItem name="X" url="/x" />
              </Navigation>
            </Document>
            
            """.trimIndent()
        )
    }

    @Test
    fun checkHtmlSnapshot() {
        val html = document.renderHtml()
        assertThat(html).isEqualTo(
            """
            <html>
              <body>
                <nav>
                  <!--this is a comment-->
                  <ul>
                    <li><a href="/a">Link A</a></li>
                    <li><a href="/b">B</a></li>
                    <li><a href="/x">X</a></li>
                  </ul>
                </nav>
              </body>
            </html>

            """.trimIndent()
        )
    }

    private fun Document.renderHtml() = StringBuilder().appendHTML().html {
        render(this)
    }.toString()

}