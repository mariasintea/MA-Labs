import 'package:discover_destination/model/destination.dart';
import 'package:discover_destination/repository/destinations_repository.dart';
import 'package:flutter/cupertino.dart';

class DestinationsViewModel with ChangeNotifier{
  DestinationsRepository repository;

  DestinationsViewModel(this.repository);

  List<Destination> getAll(String user){
    return repository.getAll(user);
  }

  Destination getOne(String city){
    return repository.getOne(city);
  }

  List<Destination> filter(String country, String user){
    return repository.getByCountry(country, user);
  }

  void add(Destination destination){
    repository.add(destination);
    notifyListeners();
  }

  void delete(String city){
    repository.delete(city);
    notifyListeners();
  }

  void modify(Destination destination){
    repository.modify(destination);
    notifyListeners();
  }
}