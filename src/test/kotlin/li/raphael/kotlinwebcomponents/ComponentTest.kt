package li.raphael.kotlinwebcomponents

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ComponentTest {
    @Test
    fun checkTxtSnapshot() {
        val nav = Navigation(Context("X")).apply {
            item("Link A", "/a")
            item("B", "/b")
            item(context.linkName, "/x")
        }
        assertThat(nav.toString()).isEqualTo(
            """
            <Navigation>
              <NavigationItem name="Link A" url="/a" />
              <NavigationItem name="B" url="/b" />
              <NavigationItem name="X" url="/x" />
            </Navigation>
            
            """.trimIndent()
        )
    }
}