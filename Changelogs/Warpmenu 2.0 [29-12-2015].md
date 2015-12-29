#PretparkCore Changelog - 29-12-2015 
##WarpMenu 2.0 Update - The "where can i go and do shit?" update


###Nieuw
* WarpMenu
  * Het oude attractie menu is helemaal weg en heeft nu een nieuw menu met een nieuwe state (BROKEN) en heeft nu ook filters! Yay for evolution!
* Commands:  
  * ```/createwarp <type> <name...>```  
    * Maakt een nieuwe warp aan in de database.  
    * De standaard state is CLOSED.
    * Type's: ```SHOP - WARP - RIDE - MAZE - PARKOUR```  
  * ```/warpstate <id> <state>```  
    * Verandert de warpstate.  
      * Kan ook vanuit een commandblock.
    * State's: ```OPEN - CLOSED - MAINTENANCE - BROKEN```  


###Aangepast
* Efficienter database gebruik.
* Sneller herkenning naar welke warp er ge tp'd moet worden.
* 78wesley staat nu in de plugin.yml!
* Attractie menu heet nu Warp Menu.
* State toegevoegd: BROKEN
* More love! :heart:
* Database Structure aangepast om het nieuwe systeem te ondersteunen.
* Made everything more stable.

###Command Instructies
------
####Een warp toevoegen:
1. Ga staan waar je wilt dat de warp moet komen, denk ook om je kijkrichting want die word namelijk ook meegenomen.
2. Open de chat en typ: ```/createwarp <type> <name...>```
3. Vervang ```<type>``` door het type.
  * De types zijn: ```SHOP - WARP - RIDE - MAZE - PARKOUR``` *(niet hoofdletter gevoelig)*
4. Vervang ```<name...>``` door de naam. Hierin zijn spaties toegestaan! *(De plugin zet er niet automatisch een lidwoord voor (de,het,een) dit moet je zelf doen, denk daar dus om! Een lidwoord is niet verplicht.)
5. Druk op enter, en wacht terwijl je attractie naar de database word gestuurd en de cache word gereloaded!
6. Gefeliciteerd! Je hebt een warp toegevoegd! :tada:

####WarpState veranderen:
1. Open het warpmenu met de minecart in je inventory, heb je die niet doe dan ```/resetinv``` of ```/rejoin```.
2. Zoek de attractie die je wil veranderen op, daarbij kun je de filters gebruiken! En onthoud of noteer het ID! *(Die staat op de laatste regel van de lore)*
3. Open de chat en typ: ```/warpstate <id> <state>```
4. Vervang ```<id>``` door het ID die je bij stap 2 hebt gekregen.
5. Vervang ```<state>``` door de state.
  * De states zijn: ```OPEN - CLOSED - MAINTENANCE - BROKEN``` *(niet hoofdletter gevoelig)*
6. Druk op enter, en een omroep komt in de chat om iedereen het te laten weten! Applausje voor je zelf! :clap:

> Omdat je de hele changelog hebt gelezen heb je hier een paar vouchers:  
> /redeem EarlyBirdWarpMenu (1x bruikbaar)  
> /redeem WarpMenu (4x bruikbaar)  
