"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.multiply = multiply;
exports.navigateToTrimmer = navigateToTrimmer;

var _reactNative = require("react-native");

const LINKING_ERROR = `The package 'react-native-k4l-video-trimmer' doesn't seem to be linked. Make sure: \n\n` + _reactNative.Platform.select({
  ios: "- You have run 'pod install'\n",
  default: ''
}) + '- You rebuilt the app after installing the package\n' + '- You are not using Expo managed workflow\n';
const K4lVideoTrimmer = _reactNative.NativeModules.K4lVideoTrimmer ? _reactNative.NativeModules.K4lVideoTrimmer : new Proxy({}, {
  get() {
    throw new Error(LINKING_ERROR);
  }

});

function multiply(a, b) {
  return K4lVideoTrimmer.multiply(a, b);
}

function navigateToTrimmer(uri, options) {
  // try {
  return K4lVideoTrimmer.navigateToTrimmer(uri, options); // } catch (error) {
  //   console.log(JSON.stringify(error));
  // }
}
//# sourceMappingURL=index.js.map