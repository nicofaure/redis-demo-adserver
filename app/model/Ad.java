package model;

import lombok.Getter;
import lombok.experimental.Builder;

@Getter
@Builder
public class Ad {

	private String title;

	private String image;

	private String clickUrl;

	private String printUrl;

	private double cpc;
}
