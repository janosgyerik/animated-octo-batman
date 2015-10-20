{
    init: function(elevators, floors) {
        function isGoingUp(elevator) {
            return elevator.goingUp || false;
        }
        function isGoingDown(elevator) {
            return elevator.goingDown || false;
        }
        function setGoingUp(elevator) {
            //elevator.goingUp = true;
            //elevator.goingDown = false;
            elevator.goingUpIndicator(true);
            elevator.goingDownIndicator(false);
        }
        function setGoingDown(elevator) {
            //elevator.goingUp = false;
            //elevator.goingDown = true;
            elevator.goingUpIndicator(false);
            elevator.goingDownIndicator(true);
        }
        function setGoingAnywhere(elevator) {
            //elevator.goingUp = null;
            //elevator.goingDown = null;
            elevator.goingUpIndicator(true);
            elevator.goingDownIndicator(true);
        }
        
        var waitingUp = [];
        var waitingDown = [];
        
        function calculateProximityBias(closeToFloorNum, floorNum) {
            return (1 - Math.abs(closeToFloorNum - floorNum) / floors.length) / 2;
        }
        
        function getFloorNumWithMostWaiting(closeToFloorNum) {
            var max = 0;
            var maxIndex = 0;
            waitingUp.forEach(function(count, index) {
                var biasedCount = calculateProximityBias(closeToFloorNum, index);
                if (biasedCount > max) {
                    max = biasedCount;
                    maxIndex = index;
                }
            });
            waitingDown.forEach(function(count, index) {
                var biasedCount = calculateProximityBias(closeToFloorNum, index);
                if (biasedCount > max) {
                    max = biasedCount;
                    maxIndex = index;
                }
            });
            return maxIndex;
        }
        
        function sanitizeQueue(elevator, up, index) {
            var unique = [];
            var queue = elevator.destinationQueue;
            queue.forEach(function(item) {
                if (unique.indexOf(item) == -1) {
                    unique.push(item);
                }
            });
            if (up) {
                unique = unique.sort();
            } else {
                unique = unique.sort(function(a, b) { b - a; });
            }
            var rotated = unique.splice(unique.indexOf(index));
            unique.forEach(function(item) {
                rotated.push(item);
            });
            elevator.destinationQueue = rotated;
            elevator.checkDestinationQueue();
        }
        
        floors.forEach(function (floor, floorNum) {
            waitingUp.push(0);
            waitingDown.push(0);
            floor.on("up_button_pressed", function() {
                waitingUp[floorNum]++;
            });
            floor.on("down_button_pressed", function() {
                waitingDown[floorNum]++;
            });
        });
        
        elevators.forEach(function (elevator, elevatorNum) {
            elevator.on("floor_button_pressed", function(floorNum) {
                elevator.goToFloor(floorNum);
            });
            
            elevator.on("idle", function() {
                var floorNum = elevator.currentFloor();
                if (floorNum == 0) {
                    setGoingUp(elevator);
                } else if (floorNum == floors.length - 1) {
                    setGoingDown(elevator);
                } else {
                    setGoingAnywhere(elevator);
                }
                elevator.goToFloor(getFloorNumWithMostWaiting(floorNum));
            });
            
            elevator.on("stopped_at_floor", function(floorNum) {
                if (floorNum == 0) {
                    setGoingUp(elevator);
                    waitingUp[floorNum] = Math.max(waitingUp[floorNum] - elevator.maxPassengerCount(), 0);
                } else if (floorNum == floors.length - 1) {
                    setGoingDown(elevator);
                    waitingDown[floorNum] = Math.max(waitingDown[floorNum] - elevator.maxPassengerCount(), 0);
                } else {
                    if (waitingUp[floorNum] > waitingDown[floorNum]) {
                        setGoingUp(elevator);
                        waitingUp[floorNum] = Math.max(waitingUp[floorNum] - elevator.maxPassengerCount(), 0);
                    } else {
                        setGoingDown(elevator);
                        waitingDown[floorNum] = Math.max(waitingDown[floorNum] - elevator.maxPassengerCount(), 0);
                    }
                }
            });
            
            elevator.on("passing_floor", function(floorNum, direction) {
                if (elevator.destinationQueue.indexOf(floorNum) != -1) {
                    var backup = elevator.destinationQueue;
                    elevator.stop();
                    elevator.destinationQueue = backup;
                    sanitizeQueue(elevator, direction == 'up', floorNum);
                }
            });
        });
    },
    update: function(dt, elevators, floors) {
        // We normally don't need to do anything here
    }
}
