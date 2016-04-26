# SimSig Gateway Interface #

The SimSig Gateway Interface is a Java programme that interfaces with a [SimSig](www.simsig.co.uk) [Gateway Interface](http://www.simsig.co.uk/dokuwiki/doku.php?id=usertrack:interface_gateway), extracting the signalling messages that are sent. These can then be used for a variety of things such as ‘live’ railway maps, and so on…

### Requirements ###

The [Strampáil](https://github.com/Gwasanaethau/Stramp-il) STOMP Client Interface is required to connect to the SimSig STOMP server. Beyond this, no other requirements other than a Java 7 JRE (or JDK if you wish to compile the code) is required to run the client interface. A SimSig loader-type simulation is required to generate the output that you wish to capture using this programme (obviously!).

### May I use this? ###

Be my guest, though it is just a proof-of-concept project for me at the moment.

### Can I use this? ###

That depends – this is very much ‘pre-α’ at the moment. It is very unlikely to become any more developed than my personal needs/intrigue requires. Chances are it will not implement much of the STOMP protocol beyond what I need it for. It may not even be useable for that. I make no guarantees regarding security or stability of the code.

**Do not use in production code!**

### Current Status ###

At the moment, the messages are just printed to the CLI as is (i.e. in their JSON format).
