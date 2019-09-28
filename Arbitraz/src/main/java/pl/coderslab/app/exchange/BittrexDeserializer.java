package pl.coderslab.app.exchange;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import pl.coderslab.app.dto.bittrex.JsonBittrexResult;

import java.io.IOException;

public class BittrexDeserializer extends StdDeserializer<JsonBittrexResult> {
    public BittrexDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public JsonBittrexResult deserialize(JsonParser parser, DeserializationContext deserializer) throws IOException {
        JsonBittrexResult jsonBittrexResult = new JsonBittrexResult();
        while(!parser.isClosed()){
            JsonToken jsonToken = parser.nextToken();

            if(JsonToken.FIELD_NAME.equals(jsonToken)){
                String fieldName = parser.getCurrentName();


                jsonToken = parser.nextToken();


                if("Bid".equals(fieldName)){
                    jsonBittrexResult.setBid(parser.getValueAsDouble());
                } else if ("Ask".equals(fieldName)){
                    jsonBittrexResult.setAsk(parser.getValueAsDouble());
                }else if ("Last".equals(fieldName)){
                    jsonBittrexResult.setLast(parser.getValueAsDouble());
                }
            }
        }
        return jsonBittrexResult;
    }

}