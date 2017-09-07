package com.s3d.sso.client;

import java.io.IOException;
import javax.servlet.ServletException;

public class EFORMSSSOChain
{
  private EFORMSSSOContext context;
  private int currentPosition = 0;

  protected EFORMSSSOChain(EFORMSSSOContext context) {
    this.context = context;
  }

  public void doNextFilter() throws IOException, ServletException {
    this.currentPosition += 1;
    if (this.currentPosition <= EFORMSSSOClient.processingFilters.length)
      EFORMSSSOClient.processingFilters[(this.currentPosition - 1)].doFilter(
        this.context, this);
    else
      EFORMSSSOUserData.getInstance().setCurrentUsername(
        this.context.getCurrentUsername());
  }
1231231233123
  public boolean isFinish()
  {
    return this.currentPosition > EFORMSSSOClient.processingFilters.length;
  }
}
