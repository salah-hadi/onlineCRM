#Region ;**** Directives created by AutoIt3Wrapper_GUI ****
#AutoIt3Wrapper_UseX64=n
#EndRegion ;**** Directives created by AutoIt3Wrapper_GUI ****
WinWaitActive("10.0.0.72:5555/QNBTest/main.aspx?appid=a2f91f12-dec1-ea11-8b9f-000c298a9dbc&pagetype=entitylist&etn=incident - Google Chrome");
Send("CRMDOMAIN\mshennawy");
Send("{TAB}");
Send("EMEit123");
Send("{ENTER}");
