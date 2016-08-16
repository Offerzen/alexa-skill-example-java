import org.json.JSONObject;

public class ResponseService {
	// Get request type from JSON request
	public static String getRequestType(JSONObject obj){
		
		JSONObject requestTypeObject = obj.getJSONObject("request");
		String requestType =  requestTypeObject.getString("type");
		
		return requestType;
	}
	
	// Get slot value for the IntentRequest
	public static String getSlotValue(JSONObject obj){
		
		JSONObject requestTypeObject = obj.getJSONObject("request");
		JSONObject intentObject = requestTypeObject.getJSONObject("intent");
		JSONObject slotsObject = intentObject.getJSONObject("slots");
		
		String name = slotsObject.getJSONObject("Name").getString("value");
	
		return name;
	}
	
	// Construct the JSON response sent back to the skill service
	public static JSONObject constructResponseObject(JSONObject obj, String requestType){
		
		String outputSpeechText;
		String outputSpeechType;
				
		if (requestType.equals("LaunchRequest")){
			
			outputSpeechText = "What name do you want me to spell out?";
			outputSpeechType = "PlainText";
			
		}
		else if (requestType.equals("IntentRequest")){
			
			String name = getSlotValue(obj);
			String ssmlName = "<say-as interpret-as=\"spell-out\">" + name + "</say-as>";
			
			outputSpeechText = "<speak>" + name + " is spelled, " + ssmlName + "</speak>";
			outputSpeechType = "SSML";
		}
		else{
			
			// Blank response for SessionEndRequest
			outputSpeechText = "";
			outputSpeechType = "PlainText";
			
		}
		
		// Construct JSON response package
		JSONObject outputSpeechElement = new JSONObject();
		outputSpeechElement.put("type", outputSpeechType);
		
		if(outputSpeechType.equals("SSML")){
			outputSpeechElement.put("ssml", outputSpeechText);
		}
		else {
			outputSpeechElement.put("text", outputSpeechText);
		}
		
		JSONObject outputSpeech = new JSONObject();
		outputSpeech.put("outputSpeech", outputSpeechElement);
		
		JSONObject responseObject = new JSONObject();
		responseObject.put("response", outputSpeech);
				
		return responseObject;
	}
	
}
