import 'package:discover_destination/model/destination.dart';

class DestinationsService{
  List<Destination> destinations = List.of([Destination("Rome", "Italy", "cultural", "Nice place", "https://thenomadvisor.com/wp-content/uploads/2020/02/romewhertostay-780x520.jpg", List.of(["Colosseum", "Trevi Fountain"]), "ana"),
  Destination("London", "UK", "cultural", "Nice place", "https://www.27vakantiedagen.nl/wp-content/uploads/2019/05/engeland-londen-palace-of-westminster.jpg", List.of(["Big Ben", "Buckingham Palace"]), "ana"),
  Destination("Paris", "France", "cultural", "Nice place", "https://images.adsttc.com/media/images/5d44/14fa/284d/d1fd/3a00/003d/large_jpg/eiffel-tower-in-paris-151-medium.jpg?1564742900", List.of(["Eiffel Tower", "Versailles Palace"]), "ion"),
  Destination("Paris1", "France", "cultural", "Nice place", "https://images.adsttc.com/media/images/5d44/14fa/284d/d1fd/3a00/003d/large_jpg/eiffel-tower-in-paris-151-medium.jpg?1564742900", List.of(["Eiffel Tower", "Versailles Palace"]), "ion")]);

  void addDestination(Destination destination){
    destinations.add(destination);
  }

  void deleteDestination(String city){
    Destination destination = getDestinationByCity(city);
    destinations.remove(destination);
  }

  void modifyDestination(Destination destination){
    int index = destinations.indexOf(getDestinationByCity(destination.city));
    destinations[index] = destination;
  }

  List<Destination> getAllDestinations(String user) {
    return destinations.where((destination) => destination.user.compareTo(user) == 0).toList();
  }

  Destination getDestinationByCity(String city){
    if (destinations.where((destination) => destination.city.compareTo(city) == 0).isNotEmpty)
      return destinations.firstWhere((destination) => destination.city.compareTo(city) == 0);
    else
      return Destination("", "", "", "", "", List.empty(), "");
  }

  List<Destination> getDestinationsByCountry(String country, String user){
    if (country.compareTo("") == 0)
      return destinations.where((destination) => destination.user.compareTo(user) == 0).toList();
    else
      return destinations.where((destination) => destination.country.compareTo(country) == 0 && destination.user.compareTo(user) == 0).toList();
  }
}