import com.hp.hpl.jena.rdf.model._
import com.hp.hpl.jena.util.FileManager
import java.io.{FileWriter, InputStream}

/**
 * Created with IntelliJ IDEA.
 * User: reinaldo
 * Date: 26/04/13
 * Time: 09:57
 * To change this template use File | Settings | File Templates.
 */
object TestingJenaScala extends App {
  val fileNameOrUri: String = "C:\\Partition\\H\\java\\workspace\\Jena\\src\\mappingbased_properties_en2.nt"
  val model: Model = ModelFactory.createDefaultModel
  val input: InputStream = FileManager.get.open(fileNameOrUri)
  if (input != null) {
    model.read(input, null, "N-TRIPLE")
    val content = new StringBuilder
    val subjects: ResIterator = model.listSubjects
    while (subjects.hasNext) {
      val subject: Resource = subjects.next
      content.append(subject.getURI)
      content.append("\t")
      val properties: StmtIterator = subject.listProperties
      while (properties.hasNext) {
        val property: Statement = properties.next
        content.append(if (property.getObject.isLiteral) property.getLiteral.getLexicalForm else property.getObject)
      }
      content.append("\n")
    }
    val output: FileWriter = new FileWriter("C:\\Partition\\H\\java\\workspace\\Jena\\src\\mappingbased_properties_en.tsv")
    output.append(content)
    output.flush
    output.close

  }
}