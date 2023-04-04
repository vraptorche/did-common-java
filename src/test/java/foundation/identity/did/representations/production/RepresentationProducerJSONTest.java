package foundation.identity.did.representations.production;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class RepresentationProducerJSONTest {

  @Test
  void produce() throws Exception {
    final RepresentationProducer.Result result = RepresentationProducerJSON.getInstance()
        .produce(new HashMap<>(), new HashMap<>());
    assertNotNull(result);
  }
}