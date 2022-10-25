package li.raphael.kotlinwebcomponents

import kotlinx.html.Tag

interface HtmlComponent<RECEIVER : Tag, CHILD_RECEIVER : Tag> : SemanticComponent {

    fun render(tag: RECEIVER)

    fun renderChildren(tagConsumer: CHILD_RECEIVER) {
        children
            .filterIsInstance<HtmlComponent<*, *>>()
            .forEach { child ->
                @Suppress("UNCHECKED_CAST")
                (child as HtmlComponent<CHILD_RECEIVER, *>).render(tagConsumer)
            }
    }

}