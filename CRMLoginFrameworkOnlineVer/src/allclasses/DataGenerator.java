package allclasses;

import java.util.logging.Level;

public class DataGenerator {
    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final String NUMERIC_STRING="0123456789";

    /**generating random strings and numbers
     * @param length the generated value length
     * @param generetorDatatype the data type of the generated value
     * @return generatedValue*/
    public static Object randomValue(int length,generetorDatatype generetorDatatype){
        StringBuilder generatedValue = new StringBuilder();
        String type=ALPHA_NUMERIC_STRING;
        if(generetorDatatype==generetorDatatype.number){
            type=NUMERIC_STRING;
        }
        if(length>0){
            while (length-- != 0) {
                int character = (int)(Math.random()*type.length());
                generatedValue.append(type.charAt(character));
            }

        }else{
            CommonParaFun.logger.log(Level.SEVERE,"Enter Valid length, It should be greater than 0");
        }

        return generatedValue;
    }

}
