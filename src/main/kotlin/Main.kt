import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import java.io.File
import javax.xml.parsers.DocumentBuilderFactory

// El siguiente fragmento lee el archivo Xml y lo transforma en un documento:
fun readXml(pathName: String): Document {
    val xmlFile = File(pathName)
    return  DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmlFile)
}

fun obtenerAtributosEnMapKV(e: Element ):MutableMap<String, String>
{
    val mMap = mutableMapOf<String, String>()
    for(j in 0..e.attributes.length - 1)
        mMap.putIfAbsent(e.attributes.item(j).nodeName, e.attributes.item(j).nodeValue)
    return mMap
}
fun obtenerListaNodosPorNombre(doc: Document, tagName: String): MutableList<Node>
{
    val bookList: NodeList = doc.getElementsByTagName(tagName)
    val lista = mutableListOf<Node>()
    for(i in 0..bookList.length - 1)
        lista.add(bookList.item(i))
    return lista
}
/*
class catologoLibrosXML {
    fun existeLibro(id: String): Boolean {
        return true
    }
    fun infoLibro(id: String):Map<String,String>{
        return
    }
}
*/

fun main() {
    var xmlDoc = readXml("https://github.com/revilofe/IESRA-DAM-Prog/ejercicios/src/main/kotlin/un5/eje5_4/items.xml")
    println("Creo DOM")


    //https://runebook.dev/es/docs/dom/node/normalize
    xmlDoc.documentElement.normalize()

    println("Nodo raíz:" + xmlDoc.documentElement.nodeName)
    val lista = obtenerListaNodosPorNombre(xmlDoc, "Item")

    lista.forEach {
        if (it.getNodeType() === Node.ELEMENT_NODE) {
            val elem = it as Element
            val mMap = obtenerAtributosEnMapKV(elem)

            println("- ${it.nodeName} - $mMap")
            println("`- Subitem: ${it.getElementsByTagName("Subitem").item(0).textContent}")
        }
    }
}