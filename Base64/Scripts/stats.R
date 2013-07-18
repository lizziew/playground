rawRH <- scan("/Base64RH/log")
rawMiG <- scan("/Base64MiG/log")

findAvg <- function(raw, start, end) {
	avg <- 0; 
	for(i in start:end) {
		avg <- avg + raw[i]; 
	}
	avg <- avg/4;
	return(avg); 
}

#dataframe
decoder_impl <- c("RH", "MiG")
K1 <- c(findAvg(rawRH, 2, 5), findAvg(rawMiG, 2, 5))
K4 <- c(findAvg(rawRH, 7, 10), findAvg(rawMiG, 7, 10))
K8 <- c(findAvg(rawRH, 12, 15), findAvg(rawMiG, 12, 15))

d <- data.frame(decoder_impl, K1, K4, K8) 

#matrix
m <- matrix(c(findAvg(rawRH, 2, 5), findAvg(rawMiG, 2, 5), findAvg(rawRH, 7, 10), findAvg(rawMiG, 7, 10), findAvg(rawRH, 12, 15), findAvg(rawMiG, 12, 15)), nrow = 2, ncol = 3)

print(d)

barplot(m, main = "Average Times", names.arg = c("1K", "4K", "8K"), xlab = "line size", ylab = "seconds", col = c("red", "blue"), legend = , beside = TRUE)
legend("topleft", inset = .05, title = "decoder impl", c("RH", "MiG"), fill = c("red", "blue"))
