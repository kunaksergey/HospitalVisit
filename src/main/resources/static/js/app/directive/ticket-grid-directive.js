var app = angular.module("myApp");

        app.directive("ticketGrid", function () {
            return {
                replace: true,
                scope: true,
                link: function (scope, element, attrs) {
                    scope.ticketSlotSubList = (scope.ticketSlotList || []).slice(attrs.start, attrs.end);
                },
                // controller: ticketGridCtrl,
                templateUrl: '/js/app/template/ticket-grid.html'
            }
        });


