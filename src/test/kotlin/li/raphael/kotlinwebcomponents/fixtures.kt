package li.raphael.kotlinwebcomponents

import kotlinx.html.*

class Context(
    val linkName: String
)

class NavigationItem(
    context: Context,
    val name: String,
    val url: String,
) : Component<Context>(context)

class Navigation(context: Context) : Component<Context>(context) {
    fun item(name: String, url: String) = initComponent(NavigationItem(context, name, url))

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
