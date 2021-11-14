import 'package:discover_destination/model/destination.dart';
import 'package:discover_destination/service/destinations_service.dart';

class DestinationsRepository {
  DestinationsService service;

  DestinationsRepository(this.service);

  void add(Destination destination) {
    service.addDestination(destination);
  }

  void delete(String city) {
    service.deleteDestination(city);
  }

  void modify(Destination destination) {
    service.modifyDestination(destination);
  }

  List<Destination> getAll(String user) {
    return service.getAllDestinations(user);
  }

  List<Destination> getByCountry(String country, String user) {
    return service.getDestinationsByCountry(country, user);
  }

  Destination getOne(String city) {
    return service.getDestinationByCity(city);
  }
}

