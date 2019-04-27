<?php

class DetailsNetflix{

	private $serieLength;

	private $serieType = array();

	private $seriePopularite;

	public function __construct($data){
		if($data["serieLength"] > 5 && $data["serieLength"] <= 25) $this->serieLength = "Short";
		elseif($data["serieLength"] > 25 && $data["serieLength"] <= 45 ) $this->serieLength = "Medium";
		elseif($data["serieLength"] > 45 && $data["serieLength"] <= 150) $this->serieLength = "Long";
		else $this->serieLength = "Une série de " . $data["serieLength"] . " minutes ? Tu nous prendrais pas pour une bille par hasard ?";
		$this->serieType = $data["serieType"];
		$this->SeriePopularite = $data["seriePopularite"];
	}

	public function compare($otherNetflixSerie){
		$note = 10;
		if($this->serieLength == "Medium" xor $otherNetflixSerie->getSerieLength() == "Medium") $note++;
		elseif(!($this->serieLength == $otherNetflixSerie->getSerieLength())) $note += 2;
		foreach ($this->serieType as $key => $type) {
			if (in_array($type, $otherNetflixSerie->getSerieType())) {
				$note--;
			}
			else $note++;
		}
		$note += abs($this->seriePopularite - $otherNetflixSerie->getSeriePopularite());
		return $note;
	}

	public function getSerieLength(){
		return $this->serieLength;
	}

	public function getSerieType(){
		return $this->serieType;
	}

	public function getSeriePopularite(){
		return $this->seriePopularite;
	}
}