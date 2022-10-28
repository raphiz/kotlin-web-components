package li.raphael.kotlinwebcomponents

import kotlinx.html.*

class Context(
    val linkName: String
)

class Document(val context: Context) : AbstractComponent<HTML, FlowContent>() {
    fun navigation(init: Navigation.() -> Unit) = initComponent(Navigation(context), init)

    override fun HTML.render() {
        body {
            renderChildren(this)
        }
    }

}

data class NavigationItem(
    val name: String,
    val url: String,
) : SemanticComponent

class Navigation(val context: Context) : AbstractComponent<FlowContent, UL>() {
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
