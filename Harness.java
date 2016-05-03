// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

package simsigGatewayInterface;

// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

/**
 * <code>Harness</code> is an interface that collects
 * SimSig messages from the SimSig server. Project-specific implementations
 * must implement this interface in order to retrieve messages.
 *
 * @author Mark David Pokorny
 * @version Dé Máirt, 3ú Bealtaine 2016
 * @since Dé Céadaoin, 27ú Aibreán 2016
 */
public interface Harness
{

// ------------------------------------------- Harness Interface ---------------

  /**
   * This method is called once the {@link SimSigClient} has connected to the
   * server and subscribed to the message feed.
   *
   * @param success Flags whether the connection procedure
   * was successful in establishing a connection.
   */
  void connected(boolean success);

// ------------------------------------------- Harness Interface ---------------

  /**
   * This method is called once the {@link SimSigClient} has parsed a
   * {@link SimSigMessage} from the server.
   */
  void transferMessage(SimSigMessage message);

// ------------------------------------------- Harness Interface ---------------

} // End ‘Harness’ Interface

// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
