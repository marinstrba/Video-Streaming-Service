export interface VideoDTO {
	    id: string;
        title: string;
        description: string;
       	tags: Array<string>;
        videoUrl: string;
        videoStatus: string;
        thumbnailUrl: string;
        likeCount: number;
        disLikeCount: number;
        viewCount: number;
}
