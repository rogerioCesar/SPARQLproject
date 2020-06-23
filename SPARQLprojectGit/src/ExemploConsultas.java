import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.util.FileManager;




public class ExemploConsultas {
	
	public static void main(String args[]) {
		
	sparqlTest();
	
	}
	
	public static void sparqlTest() {
		//FileManager.get().addLocatorClassLoader(ApsbApplication.class.getClassLoader());
		Model model = FileManager.get().loadModel("C:\\Users\\Rogerio\\git\\SPARQLproject\\SPARQLprojectGit\\soda.rdf");
		String Prefix ="PREFIX brick: <https://brickschema.org/schema/1.1/Brick>";
		String queryString = "SELECT ?room ?sensor WHERE {" + 
				"?sensor rdf:type/rdfs:subClassOf+ brick:Temperature_Sensor ."
			  + "?sensor bf:hasLocation ?room ."
			  + "?room rdf:type/rdfssubClassOf+ brick:Room ." + 
				"}";
		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query,model);
		try {
			ResultSet results = qexec.execSelect();
			while (results.hasNext()) {
				QuerySolution sl = results.nextSolution();
				Literal name = sl.getLiteral("sensor");
				System.out.println(name);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			qexec.close();
		}
		}
}
