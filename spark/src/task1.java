import org.apache.spark.SparkConf;
import org.apache.spark.api.java.*;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.ml.classification.MultilayerPerceptronClassificationModel;
import org.apache.spark.ml.classification.MultilayerPerceptronClassifier;
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.mllib.util.MLUtils;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;

class StatePopulationSum {
    public static void main(String[] args) {
        String path = "/Users/ravitheja/Documents/Projects/UCR-CS236-Project/data/SupplementalDataCounty.csv";
        SparkConf conf = new SparkConf().setAppName("Simple ANN");
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> data = sc.textFile("/Users/ravitheja/Documents/Projects/UCR-CS236-Project/data/SupplementalDataCounty.csv");
        System.out.println("data is ::" + data);
        SQLContext sqlContext = new SQLContext(sc); // In Spark 1.3 the Java API and Scala API have been unified


        JavaRDD<Record> rdd_records = sc.textFile(data).map(
        new Function<String, Record>() {
            public Record call(String line) throws Exception {
                String[] fields = line.split(",");
                Record sd = new Record(fields[0], fields[1].trim(), fields[2], fields[3], fields[4]);
                System.out.println("record is :: " + sd.toString());
                return sd;
            }
        });
    }
}

class Record implements Serializable {
  String fips;
  String state;
  String county;
  String variable_code;
  String value;

  public getFips() {
    return this.fips;
  }

  public getState() {
    return this.state;
  }

  public getCounty() {
    return this.county;
  }

  public getVariableCode() {
    return this.variable_code;
  }

  public getValue() {
    return this.value;
  }

  public getFips(String fips) {
    this.fips = fips;
  }

  public getState(String state) {
    this.state = state;
  }

  public getCounty(String county) {
    this.county = county;
  }

  public getVariableCode(String variableCode) {
    this.variable_code = variableCode;
  }

  public getValue(String value) {
    this.value = value;
  }

  public String toString() {
    System.out.println(fips + " " + state + " " + county + " " + variable_code + " " + value);
  }

  Record(String fips, String state, String county, String variable_code, String value) {
    this.fips = fips;
    this.state = state;
    this.county = county;
    this.variable_code = variable_code;
    this.value = value;
  }

}
