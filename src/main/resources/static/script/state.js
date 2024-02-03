"use strict";
/*enum state {
    SAVE = "save-button",
    DELETE = "delete-button"
}*/
var state;
(function (state) {
    state["SAVE"] = "save";
    state["DELETE"] = "delete";
})(state || (state = {}));
