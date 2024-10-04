package fr.balijon.centrale.jsonviews;

public class JsonViews {

    public interface UserMinimalView{}
    public interface UserShow extends UserMinimalView {}

    public interface ListingListView {}
    public interface ListingShow extends ListingListView {}

    public interface ModelListView {}
    public interface ModelShow extends ModelListView {}

    public interface BrandListView {}
    public interface BrandShow extends BrandListView{}

    public interface AddressListView {}
    public interface AddressShow extends AddressListView{}

    public interface FuelListView{}
}
