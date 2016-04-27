// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

package simsigGatewayInterface;
import java.util.HashMap;

// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

/**
 * Contains the details of a single SimSig message.
 *
 * @author Mark David Pokorny
 * @version Dé Céadaoin, 27ú Aibreán 2016
 * @since Dé Céadaoin, 27ú Aibreán 2016
 */
class SimSigMessage
{

// ----------------------------------------- SimSigMessage Class ---------------

  private MessageType type;
  private HashMap<String, String> parameters;

// ----------------------------------------- SimSigMessage Class ---------------

  SimSigMessage(MessageType type, HashMap<String, String> parameters)
  {
    this.type = type;
    this.parameters = parameters;
  } // End ‘SimSigMessage(MessageType, HashMap<String, String>)’ Constructor

// ----------------------------------------- SimSigMessage Class ---------------

  MessageType getType(){ return type; }
  HashMap<String, String> getParameters(){ return parameters; }

// ----------------------------------------- SimSigMessage Class ---------------

} // End ‘SimSigMessage’ Class

// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
