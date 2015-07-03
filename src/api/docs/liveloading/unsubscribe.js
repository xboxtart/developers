import Method from "../../method.js";
import Parameter from "../../parameter.js";
import Example from "../../example.js";

import ErrorfulResponse from "../../errorfulResponse.js";

export default class LiveloadingUnsubscribe extends Method {
  uri() { return "/api/v1/live"; }
  version() { return 1; }
  httpMethod() { return "DELETE"; }
  description() { return "This endpoint unsubscribes the associated socket " +
                         "with an event that it previously subscribed to."; }
  parameters() { return [ new SlugParameter(), new MetaParameter() ]; }
  examples() { return [ new SuccesfulExample(), new ErrorfulResponse() ]; }
}

class SlugParameter extends Parameter {
  name() { return "slug"; }
  description() { return "Passed as a string or array of strings. The name(s) " +
                        "of the interface you wish to subscribe to (see above)."; }
}

class MetaParameter extends Parameter {
  name() { return "meta"; }
  description() { return "Additional data which can be passed as needed to the" +
                         " interface."; }
  optional() { return true; }
}

class SuccesfulExample extends Example {
  httpCode() { return 200; }
  data() { return "Unsubscribed succesfully."; }
}
