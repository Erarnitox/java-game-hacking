public enum GameMode {
	FFA((byte)0,false),CoopEdit((byte)1,false),TeamPlay((byte)2,true),Instagib((byte)3,false),InstagibTeam((byte)4,true),Efficiency((byte)5,false),EfficiencyTeam((byte)6,true),
	Tactics((byte)7,false), TacticsTeam((byte)8,true), Capture((byte)9,false), RegenCapture((byte)10,true), CTF((byte)11,true), InstaCTF((byte)12,true), EfficencyCTF((byte)17,true),
	Protect((byte)13,true), InstaProtect((byte)14,true), EfficiencyProtect((byte)18,true), Hold((byte)15,true), InstaHold((byte)16,true), EfficiencyHold((byte)19,true),
	Collect((byte)20, true), InstaCollect((byte)21, true), EfficiencyCollect((byte)22, true), MainMenu((byte)255, false), Invasion((byte)254, false), Campaign((byte)253, false);
	
	public final byte value;
	public final boolean isTeamMode;
	
	GameMode(byte value, boolean teamMode){
		this.value = value;
		this.isTeamMode = teamMode;
	}
}
