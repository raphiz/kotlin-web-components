package li.raphael.kotlinwebcomponents

import kotlinx.html.*

class Context(
    val linkName: String
)

class Document(context: Context) : Component<Context, HTML, FlowContent>(context) {
    fun navigation(init: Navigation.() -> Unit) = initComponent(Navigation(context), init)

    override fun render(tag: HTML) {
        tag.apply {
            body {
                renderChildren(this)
            }
        }
    }

}

data class NavigationItem(
    val name: String,
    val url: String,
) : SemanticComponent

class Navigation(context: Context) : Component<Context, FlowContent, UL>(context) {
    fun item(name: String, url: String) = initComponent(NavigationItem(name, url))


    override fun render(tag: FlowContent) {
        tag.apply {
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
}
