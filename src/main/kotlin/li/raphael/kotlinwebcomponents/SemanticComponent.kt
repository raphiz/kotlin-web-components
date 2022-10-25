package li.raphael.kotlinwebcomponents

import kotlin.reflect.KVisibility
import kotlin.reflect.full.declaredMemberProperties

interface SemanticComponent {
    val children: List<SemanticComponent>
        get() = emptyList()
    val elementAttributes: Map<String, Any?>
        get() = this::class.declaredMemberProperties
            .filter { it.visibility == KVisibility.PUBLIC }
            .associate { it.name to it.call(this) }
    val elementName: String
        get() = this::class.simpleName!!
}