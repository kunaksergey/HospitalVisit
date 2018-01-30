angular
    .module("myApp", ['ui.bootstrap'])
    .config(function (uibDropdownConfig) {
        uibDropdownConfig.openClass = 'show';
    });