package li.raphael.kotlinwebcomponents

import kotlinx.html.*

class Context(
    val linkName: String
)

data class NavigationItem(
    val name: String,
    val url: String,
) : SemanticComponent

class Navigation(context: Context) : Component<Context>(context) {
    fun item(name: String, url: String) = initComponent(NavigationItem(name, url))

    override fun FlowContent.render() {
        nav {
            comment("this is a comment")
            ul {
                children.forEach {
                    val navItem = it as NavigationItem
                    li {
                        a(href = navItem.url) {
                            +navItem.name
                        }
                    }
                }
            }
        }

    }
}
