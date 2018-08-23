# Task
## 1. Create ParkingLot
```
Given parkinglot's name and size
When create a new parkinglot
Then successfully created
```

## 2. Update ParkingLot's infomation successfully
```
Given parkinglot's id and update info
When update a parkinglot
Then successfully update
```

## 3. Update ParkingLot's infomation unsuccessfully
```
Given nonexistent parkinglot's id and update info
When update a parkinglot
Then unsuccessfully update
```
## 4. Freeze ParkingLots successfully
```
Given a parkingLot's id
When freeze a parkinglot
Then successfully freezed
```

## 5. Freeze ParkingLots unsuccessfully when parkinglot id nonexistent
```
Given a nonexistent parkingLot's id
When freeze a parkinglot
Then unsuccessfully freezed
```

## 6. Freeze ParkingLots unsuccessfully when parkinglot has cars
```
Given a parkingLot's id that has cars
When freeze a parkinglot
Then unsuccessfully freezed
```