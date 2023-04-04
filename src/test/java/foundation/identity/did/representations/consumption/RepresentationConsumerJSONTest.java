package foundation.identity.did.representations.consumption;

import foundation.identity.did.DIDDocument;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class RepresentationConsumerJSONTest {
  @Test
  void consume() throws Exception {
    final DIDDocument didDocument = DIDDocument.builder().build();
    final RepresentationConsumer.Result result = RepresentationConsumerJSON.getInstance()
        .consume(didDocument.toString().getBytes(StandardCharsets.UTF_8));
    assertNotNull(result);
  }
}