package data.scripts;

import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.impl.campaign.ids.Conditions;
import java.util.ArrayList;
import java.util.List;
import com.fs.starfarer.api.campaign.PlanetAPI;

public class MarketConditionTweaks extends BaseModPlugin {
	
    private static void tryAddCondition(String marketName, String condition) {
		tryAddCondition(Global.getSector().getEconomy().getMarket(marketName), condition);
    }
	private static void tryAddCondition(PlanetAPI marketPlanet, String condition) {
		if(marketPlanet!=null) {
			tryAddCondition(marketPlanet.getMarket(), condition);
		}
    }
	private static void tryAddCondition(MarketAPI market, String condition) {
		if(market!=null) {
			if(!market.hasCondition(condition)) {
				market.addCondition(condition);
				market.getCondition(condition).setSurveyed(true);
			}
		}
    }
	
	private static void tryRemoveCondition(String marketName, String condition) {
		tryRemoveCondition(Global.getSector().getEconomy().getMarket(marketName), condition);
	}
	private static void tryRemoveCondition(PlanetAPI marketPlanet, String condition) {
		if(marketPlanet!=null) {
			tryRemoveCondition(marketPlanet.getMarket(), condition);
		}
    }
	private static void tryRemoveCondition(MarketAPI market, String condition) {
		if(market!=null) {
			if(market.hasCondition(condition)) {
				market.removeCondition(condition);
			}
		}
    }
	
	private static void tryAddIndustry(String marketName, String industry) {
		MarketAPI market = Global.getSector().getEconomy().getMarket(marketName);
		if(market!=null) {
			if(!market.hasIndustry(industry)) {
				market.addIndustry(industry);
			}
		}
    }
	private static void tryRemoveIndustry(String marketName, String industry) {
		MarketAPI market = Global.getSector().getEconomy().getMarket(marketName);
		if(market!=null) {
			if(market.hasIndustry(industry)) {
				market.removeIndustry(industry, null, false);
			}
		}
    }
	private static void tryImproveIndustry(String marketName, String industry) {
		MarketAPI market = Global.getSector().getEconomy().getMarket(marketName);
		if(market!=null) {
			if(market.hasIndustry(industry)) {
				market.getIndustry(industry).setImproved(true);
			}
		}
    }
	
	private static PlanetAPI getNonMarketPlanet(String system, String planet) {
		List<PlanetAPI> system_planets = Global.getSector().getStarSystem(system).getPlanets();
		for(int i=0;i<system_planets.size();i++) {
			PlanetAPI possible_planet = (PlanetAPI)system_planets.get(i);
			if(possible_planet.getId() == planet) {
				return possible_planet;
			}
		}
		return null;
	}
	

    @Override
    public void onGameLoad(boolean newGame) {
        //Global.getLogger(this.getClass()).info("Hooray My mod plugin in a jar is loaded!");
		
		if(newGame) {
			//Arcadia
			PlanetAPI nomios = getNonMarketPlanet("Arcadia", "nomios");
			nomios.changeType("rocky_ice", null);
			tryRemoveCondition("nomios", "thin_atmosphere");
			tryAddCondition("nomios", "no_atmosphere");
			tryRemoveCondition("nomios", "organics_trace");
			tryRemoveCondition("nomios", "volatiles_abundant");
			tryAddCondition("nomios", "volatiles_diffuse");
			
			//Askonia
			tryRemoveCondition("cruor", "thin_atmosphere");
			tryAddCondition("cruor", "no_atmosphere");
			tryRemoveCondition("umbra", "thin_atmosphere");
			tryAddCondition("umbra", "no_atmosphere");
			
			//Aztlan
			tryRemoveCondition("chicomoztoc", "habitable");
			tryRemoveCondition("chicomoztoc", "farmland_poor");
			
			//Canann
			tryAddCondition("asher", "organics_common");
			
			//Corvus
			tryAddCondition("asharu", "extreme_weather");
			tryAddCondition("jangala", "hot");
			tryAddCondition("corvus_IIIa", "tectonic_activity");
			tryAddCondition("corvus_IIIa", "ore_sparse");
			tryRemoveCondition("corvus_IIIa", "cold");
			tryAddCondition("corvus_IIIa", "very_cold");
			
			//Eos Exodus
			tryAddCondition("tartessus", "hot");
			tryRemoveCondition("tartessus", "organics_trace");
			tryAddCondition("tartessus", "organics_common");
			tryRemoveCondition("baetis", "thin_atmosphere");
			tryAddCondition("baetis", "no_atmosphere");
			tryRemoveCondition("baetis", "volatiles_plentiful");
			tryAddCondition("baetis", "volatiles_trace");
			tryRemoveCondition("hesperus", "organics_abundant");
			tryRemoveCondition("hesperus", "thin_atmosphere");
			tryAddCondition("hesperus", "no_atmosphere");
			
			//Galatia
			tryAddCondition("ancyra_market", "farmland_poor");
			tryAddCondition("ancyra_market", "organics_common");
			
			//Hybrasil
			tryAddCondition("eochu_bres", "cold");
			tryRemoveCondition("eochu_bres", "organics_trace");
			tryAddCondition("eochu_bres", "organics_common");
			PlanetAPI crom_cruach = getNonMarketPlanet("Hybrasil", "crom_cruach");
			tryRemoveCondition(crom_cruach, "hot");
			tryAddCondition(crom_cruach, "cold");
			tryRemoveCondition(crom_cruach, "thin_atmosphere");
			tryAddCondition(crom_cruach, "no_atmosphere");
			tryRemoveCondition("cethlenn", "cold");
			tryAddCondition("cethlenn", "very_cold");
			tryAddCondition("cethlenn", "tectonic_activity");
			tryRemoveCondition("cethlenn", "organics_common");
			tryAddCondition("cethlenn", "ore_sparse");
			tryAddCondition("cethlenn", "volatiles_trace");
			tryRemoveCondition("donn", "volatiles_abundant");
			tryAddCondition("donn", "volatiles_trace");
			
			//Kumari Kandam
			tryAddCondition("chalcedon", "poor_light");
			tryRemoveCondition("chalcedon", "organics_trace");
			tryAddCondition("chalcedon", "organics_common");
			tryAddCondition("olinadu", "tectonic_activity");
			tryRemoveCondition("olinadu", "cold");
			tryAddCondition("olinadu", "very_cold");
			tryRemoveCondition("olinadu", "organics_trace");
			tryAddCondition("olinadu", "ore_sparse");
			tryRemoveCondition("olinadu", "volatiles_abundant");
			tryAddCondition("olinadu", "volatiles_plentiful");
			
			//Magec
			PlanetAPI maxios = getNonMarketPlanet("Magec", "maxios");
			tryRemoveCondition(maxios, "thin_atmosphere");
			tryAddCondition(maxios, "no_atmosphere");
			tryRemoveCondition("tibicena", "tectonic_activity");
			
			//Naraka
			tryAddCondition("yama", "hot");
			tryAddCondition("yama", "organics_common");
			tryAddCondition("nachiketa", "hot");
			
			//Samarra
			tryRemoveCondition("eventide", "volatiles_abundant");
			tryAddCondition("eventide", "volatiles_diffuse");
			tryRemoveCondition("eventide", "organics_trace");
			tryAddCondition("eventide", "organics_common");
			tryRemoveCondition("orthrus", "thin_atmosphere");
			tryAddCondition("orthrus", "no_atmosphere");
			
			//Thule
			tryAddCondition("kazeron", "no_atmosphere");
			tryRemoveCondition("thule_pirate_station", "cold");
			
			//Tyle
			tryAddCondition("cibola", "hot");
			tryAddCondition("cibola", "extreme_weather");
			tryRemoveCondition("cibola", "organics_trace");
			tryAddCondition("madeira", "cold");
			tryAddCondition("madeira", "organics_common");
			tryRemoveCondition("madeira", "volatiles_diffuse");
			tryAddCondition("madeira", "volatiles_trace");
			tryRemoveCondition("madeira", "population_6");
			tryAddCondition("madeira", "population_5");
			
			//Valhalla
			tryAddCondition("raesvelg", "ore_sparse");
			tryAddCondition("skathi", "ore_sparse");
			
			//Westernesse
			PlanetAPI horn = getNonMarketPlanet("Westernesse", "horn");
			horn.changeType("lava_minor", null);
			tryAddCondition("fikenhild", "organics_common");
			tryRemoveCondition("suddene", "habitable");
			tryAddCondition("ailmar", "organics_common");
			
			//Yma
			PlanetAPI hanan_pacha = getNonMarketPlanet("Yma", "hanan_pacha");
			tryAddCondition(hanan_pacha, "thin_atmosphere");
			tryRemoveCondition("salamanca", "dense_atmosphere");
			tryAddCondition("salamanca", "toxic_atmosphere");
			tryAddCondition("salamanca", "ore_sparse");
			tryAddCondition("qaras", "cold");
			
			//Zagan
			tryAddCondition("mazalot", "organics_common");
			tryAddCondition("ilm", "ore_sparse");
			tryRemoveCondition("ilm", "cold");
			tryAddCondition("ilm", "very_cold");
			tryImproveIndustry("ilm", "mining");
			
			
		}
    }
}